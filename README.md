# SpringBoot-JWT-Admin

This is an Eclipse project. Nevertheless, it can be used via console, or imported into NetBeans / IntelliJ IDEA. Libraries are imported via Maven to ease dependency management. You should always use the newest version of dependency before starting.

This Rest Application is used to perform all derived query. You can get user info data on the basis of different criteria.
Run & Put a breakpoint into the debugger and start playing around. This is very simple example for getting started.
# Steps to run SpringBoot-Jwt-Admin project::
# Step-1
    we are disabling spring security for One Rest-endPoint i.e /authenticate
    for generating JWT- token.
    hit URL:: http://localhost:8080/authenticate endpoint with POST method and content-Type is application/json and 
     in the Body provide username and pass.. in json format i.e
     {
	   "userName" :"admin",
	   "password":"admin@123"
     }
     you will get JWT-token like this
     JWT Token :: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNYWRodXIiLCJleHAiOjE1OTU5NjEzMDcsImlhdCI6MTU5NTkyNTMwN30.fjbEkYyWk7IAKXfDlzMY374fhB2IFlRPg_5JcWlrbJU
    After verifying client , we can access any endpoint of our application  with this JWT-token.
    hit any endpoint with header provide one key.
       ==   Authorization is Bearer your-JWT-token. 
       
# Step-2
     Insert employee details by URL: http://localhost:8080/emp/upload with Http Post Method(don't put any content-Type in header).
       
# Step-3
     Get employee details by empId.
     hit :: http://localhost:8080/emp/findbyid/1 with Http Get method.
    
# Step-4
     Get All employees detail by URL: http://localhost:8080/emp/findall with Http Get Method. 
     
# Step-5
     Update employee detail by URL: http://localhost:8080/emp/update with Http Put Method 
     and provide already available employee Id in the method body. 
     
# Step-6
     Delete employee by empId by URL: http://localhost:8080/emp/deletebyid/1 with Http Delete Method.
     

Best regards, Madhur Chouhan
