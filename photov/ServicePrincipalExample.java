package com.microsoft.azure.samples.authentication;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.azure.management.compute.ComputeManagementClient;
import com.microsoft.azure.management.compute.ComputeManagementService;
import com.microsoft.azure.management.compute.VirtualMachineOperations;
import com.microsoft.azure.management.compute.VirtualMachineOperationsImpl;
import com.microsoft.azure.management.compute.models.VirtualMachineGetResponse;
import com.microsoft.azure.management.compute.models.VirtualMachineListResponse;
import com.microsoft.azure.management.network.models.ProvisioningState;
import com.microsoft.azure.management.resources.ResourceManagementClient;
import com.microsoft.azure.management.resources.ResourceManagementService;
import com.microsoft.azure.management.resources.models.GenericResourceExtended;
import com.microsoft.azure.management.resources.models.Provider;
import com.microsoft.azure.management.resources.models.ResourceGroupExtended;
import com.microsoft.azure.management.resources.models.ResourceListParameters;
import com.microsoft.azure.management.resources.models.ResourceListResult;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.DynamicTableEntity;
import com.microsoft.azure.storage.table.EntityResolver;
import com.microsoft.windowsazure.Configuration;
import com.microsoft.windowsazure.management.ManagementClient;
import com.microsoft.windowsazure.management.ManagementService;
import com.microsoft.windowsazure.management.configuration.ManagementConfiguration;
import com.microsoft.windowsazure.management.scheduler.CloudServiceManagementClient;
import com.microsoft.windowsazure.management.scheduler.CloudServiceManagementService;
import com.microsoft.windowsazure.serviceruntime.RoleEnvironment;
import com.microsoft.windowsazure.services.table.client.TableConstants;
import com.microsoft.windowsazure.services.table.client.TableQuery.Operators;
import com.microsoft.windowsazure.services.table.client.TableQuery.QueryComparisons;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.table.client.TableConstants;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableQuery;
import com.microsoft.windowsazure.services.table.client.TableQuery.Operators;
import com.microsoft.windowsazure.services.table.client.TableQuery.QueryComparisons;
import com.nimbusds.openid.connect.sdk.util.Resource;

import javax.naming.ServiceUnavailableException;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.persistent.azure.diagnostics.performancecounters.WADPerfCountersEntity;
import com.persistent.azure.diagnostics.performancecounters.WADPerfCountersEntityResolver;

/**
 * Service Principal Example
 * <p>
 * This example will show you how to make a request to Azure Resource Manager
 * (ARM) using a ADAL4J and Azure Active Directory. You will need the following:
 * <p>
 * <ol>
 * <li>Your Azure Active Directory Tenant ID</li>
 * <li>Your Azure Active Directory Subscription ID</li>
 * <li>Your Azure Active Directory Application Client ID</li>
 * <li>Your Azure Active Directory Application Client Secret</li>
 * </ol>
 * <p>
 * After gathering or creating each of those items, you should be able to add
 * them to the source code below to execute your first authenticated request
 * using a Service Principal with RBAC in Azure. Each of the items below are
 *
 * @see <a
 *      href="https://azure.microsoft.com/en-us/documentation/articles/resource-group-create-service-principal-portal/">Creating
 *      a Service Principal</a> </p>
 */
public class ServicePrincipalExample {

	public static final String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=mgcs4419369;"
			+ "AccountKey=NaeFIYt+O/yFkbx8MYmZ5MkOobE+BW2i5L8r9Izgx1bO3win88Tn0j4/Q1TWtNiScfmR4Bk1f+o8x35RKxV8Sw==";

	/**
	 * Request a listing of all resource groups within a subscription using a
	 * service principal for authentication.
	 *
	 * @param args
	 *            arguments supplied at the command line (they are not used)
	 * @throws Exception
	 *             all of the exceptions!!
	 */
	public static void main(String[] args) throws Exception {
		ResourceManagementClient client = ServicePrincipalExample.createResourceManagementClient();
		ArrayList<ResourceGroupExtended> groups = client.getResourceGroupsOperations().list(null).getResourceGroups();
		ArrayList<Provider> providers = client.getProvidersOperations().list(null).getProviders();
		
		ArrayList<GenericResourceExtended> resources = client.getResourcesOperations().list(null).getResources();
		System.out.println("\n\n\n");
		for(Provider provider: providers)
		{
			System.out.println("Provider is: " + provider.getNamespace());
		}
		
		System.out.println("\n\n\n");
		
		System.out.println("Going to print groups: " + client.getResourceGroupsOperations().get("mgcs441").getStatusCode());
		for (ResourceGroupExtended group : groups) {
			System.out.println(group.getName());
			ResourceListParameters params = new ResourceListParameters();
			params.setResourceGroupName(group.getName());
			ArrayList<GenericResourceExtended> resList = client.getResourcesOperations().list(params).getResources();
			for (GenericResourceExtended r : resList) {
				System.out.println("Resource Type: " + r.getType() + " name: " + r.getName());
			}
		}

		for (GenericResourceExtended resource : resources) {
			System.out.println("Type: " + resource.getType());
			System.out.println("Resource: " + resource.getName());
		}

		Configuration config = createConfiguration();
		ComputeManagementClient cloudMgmtService = ComputeManagementService.create(config);
		
		
		
		VirtualMachineOperations vmOps = cloudMgmtService.getVirtualMachinesOperations();
		
	//	vmOps.start("mgcs441", "mg3cs441","mg3cs441");

		CloudStorageAccount account = CloudStorageAccount.parse(storageConnectionString);

		// Create a table service client.
		CloudTableClient tableClient = account.createCloudTableClient();


		
		Calendar currentTime = Calendar.getInstance();
		// Create a filter for 'timestamp less than current time'
		String upperBound = TableQuery.generateFilterCondition(
				TableConstants.TIMESTAMP,
				QueryComparisons.LESS_THAN,
				currentTime.getTime());
		
		currentTime.add(Calendar.MINUTE, -10000);
		
		// Create a filter for 'timestamp greater than (current time - 5 min)'
		String lowerBound = TableQuery.generateFilterCondition(
				TableConstants.TIMESTAMP,
				QueryComparisons.GREATER_THAN,
				currentTime.getTime());
		
		//UNCOMMENT
		String filter = TableQuery.combineFilters(upperBound, Operators.AND, lowerBound);
		TableQuery<WADPerfCountersEntity> query = TableQuery.from("WADPerformanceCountersTable",WADPerfCountersEntity.class);
		WADPerfCountersEntityResolver resolver = new WADPerfCountersEntityResolver();
		
		// System.out.println("yess -"+table.execute(query,resolver));
		Iterable<WADPerfCountersEntity> ita = tableClient.execute(query, resolver);
		for (WADPerfCountersEntity wpf : ita) {
			System.out.println("Role is: " + wpf.getRole());
			System.out.println("Role instance is: " + wpf.getRoleInstance());
			System.out.println(wpf.getCounterName() + " ... " + wpf.getCounterValue());
		}

	}

	/**
	 * Use the ResourceManagementService factory helper method to create a
	 * client based on the management config.
	 *
	 * @return ResourceManagementClient a client to be used to make
	 *         authenticated requests to the ARM REST API
	 * @throws Exception
	 *             all of the exceptions
	 */
	protected static ResourceManagementClient createResourceManagementClient() throws Exception {
		Configuration config = createConfiguration();
		return ResourceManagementService.create(config);
	}

	/**
	 * Create configuration builds the management configuration needed for
	 * creating the ResourceManagementService. The config contains the baseURI
	 * which is the base of the ARM REST service, the subscription id as the
	 * context for the ResourceManagementService and the AAD token required for
	 * the HTTP Authorization header.
	 *
	 * @return Configuration the generated configuration
	 * @throws Exception
	 *             all of the exceptions!!
	 */
	public static Configuration createConfiguration() throws Exception {
		String baseUri = "https://management.core.windows.net/";
		return ManagementConfiguration.configure(null, new URI(baseUri),
		// TODO: add your subscription id
				"ab96aeb3-0455-40af-8a96-bca87bd2e008", getAccessTokenFromServicePrincipalCredentials().getAccessToken());
	}

	/**
	 * Get access token from service principal credentials calls ADAL4J to get a
	 * Bearer Auth token to use for the ARM REST API.
	 *
	 * @return AuthenticationResult the result of the request to Azure Active
	 *         Directory via ADAL4J
	 * @throws ServiceUnavailableException
	 *             something broke when making a call to Azure Active Directory
	 * @throws MalformedURLException
	 *             the url provided to AAD was not properly formed
	 * @throws ExecutionException
	 *             houston we have a problem.
	 * @throws InterruptedException
	 *             the request to AAD has been interrupted
	 */
	private static AuthenticationResult getAccessTokenFromServicePrincipalCredentials() throws ServiceUnavailableException, MalformedURLException,
			ExecutionException, InterruptedException {
		AuthenticationContext context;
		AuthenticationResult result = null;
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(1);
			// TODO: add your tenant id
			context = new AuthenticationContext("https://login.windows.net/" + "e202cd47-7a56-4baa-99e3-e3b71a7c77dd", false, service);
			// TODO: add your client id and client secret
			ClientCredential cred = new ClientCredential("6da5176b-a1f9-4e65-b556-91cfb4a63695", "s6Eb9sFjCVNwXqCn2DbeiGWKUy4jc1qAE4r+hlsfgAc=");
			Future<AuthenticationResult> future = context.acquireToken("https://management.core.windows.net/", cred, null);
			result = future.get();
		} finally {
			service.shutdown();
		}

		if (result == null) {
			throw new ServiceUnavailableException("authentication result was null");
		}
		return result;
	}
}
