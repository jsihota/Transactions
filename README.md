I implement restful api call using java and spring boot

# Build and Test #

1. Open your command line
2. Go to the root director project (where the pom.xml file is located)
3. Type mvn clean
4. Type mvn install

# Run the service #
1. Go to the target folder
2. Type java -jar transactions-0.0.1-SNAPSHOT.jar

# Test call #
1. GetAllTransactions
   * call http://localhost:8080/all
2. GetAllTransactions ignore donuts 
   * call http://localhost:8080/all?ignore=donuts
3. GetAllTransactions ignore cc-payments 
   * call http://localhost:8080/all?ignore=payment
4.  crystal-ball
   * call http://localhost:8080/predictNextMonth

# Response #
1. all call 
    * id                     -> user id 
    * report
        *  lineItem          -> line items 
        *  ignoredLineItem:  -> ignore transactions 
        *  average           -> Average  
   
   
2.predictNextMonth call 
  * average: 
      * spending              -> predicated spending 
      * income                -> predicated income 
      * id                    -> user id
      * month                 -> predicated month
      * year                  -> predicated year 



    
   
   
Future improvements
1. Add cache for transaction to handle load 
2. Dockerize so easy to deploy and test 
