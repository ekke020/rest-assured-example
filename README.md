# rest-assured-example

Maven & Java 17 with a first failing test in JUnit 5.
Restassured test and container deploy to Azure

## Deploy Credentials

1. Create a resource group for this purpose
2. Create a RBAC role, replace {subscription-id} and {resource-group}
  
  ```bash
  az ad sp create-for-rbac --name "" --role contributor \
                            --scopes /subscriptions/{subscription-id}/resourceGroups/{resource-group} \
                            --sdk-auth
                            
  # Replace {subscription-id}, {resource-group} with the subscription, resource group details

  # The command should output a JSON object similar to this:

 
  {
    "clientId": "<GUID>",
    "clientSecret": "<STRING>",
    "subscriptionId": "<GUID>",
    "tenantId": "<GUID>",
    "resourceManagerEndpointUrl": "<URL>"
    (...)
  }
  
  ```
4. Add the json response to a Github repository secret named `AZURE_CREDENTIALS`
3. We will use the [azure login action](https://github.com/Azure/login)
5. Create a action to test your login: 
   
   ```yml
   azure-deploy:
    runs-on: ubuntu-latest
    steps:
      - name: "Login via Azure CLI"
        uses: azure/login@v1
        with:
          creds: ${{ secrets.AZURE_CREDENTIALS }}
   ```
