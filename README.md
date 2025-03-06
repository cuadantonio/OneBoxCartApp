# OneBox Cart App

App created for a OneBox trial where you can manage a simple ecommerce cart.

To run the app you have two options:

- **Maven and Java JDK 17:**  
  If you choose this option, first you have to make sure Maven and Java are installed by running these commands:
  
  ```sh
  mvn -v
  javac -version
  java -version
  ```
  
  Then you have to go to the project main folder and run the next command:
  
  ```sh
  mvn clean install
  ```
  
  After this command, a new folder called `target` containing a JAR file must have been created with the name `OneBoxCartApp-1.0.0.jar`.  
  Then run this command:
  
  ```sh
  java -jar ./target/OneBoxCartApp-1.0.0.jar
  ```
  
  The app should be running. To stop, press `Ctrl + C`.

- **Docker:**  
  If you choose this option, first you have to make sure Docker is installed by running this command:
  
  ```sh
  docker -v
  ```
  
  Then run the following command:
  
  ```sh
  docker run -d -p 8080:8080 cuadantonio/oneboxcartapp:latest
  ```
  
  To stop, first you have to run this command:
  
  ```sh
  docker ps
  ```
  
  A list should appear with all your containers. Check for the name of the container using the `cuadantonio/oneboxcartapp` image.  
  Finally, run this command:
  
  ```sh
  docker stop <containername>
  ```
  
  Replace `<containername>` with the actual name of the container.

  To test the app you can use the `CartsApp.postman_collection.json` file on Postman.

