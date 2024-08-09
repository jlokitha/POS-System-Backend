# POS System Backend

## Overview
A backend service for a Point of Sale (POS) system designed to manage customer and item data efficiently. This service facilitates the creation, retrieval, updating, and deletion of records, and provides capabilities for saving and retrieving order information.

## Technologies Used
The following technologies were used in the development of the Backend Server:

- **JakarthaEE**
- **MySQL**
- **Maven**
- **Tomcat**
- **JNDI**

## API endpoints
- **Customer Management:**
  - Create new customer records.
  - Read and retrieve customer details.
  - Update existing customer information.
  - Delete customer records as needed.

- **Item Management:**
  - Add new items to the inventory.
  - Retrieve item details and stock levels.
  - Update item information and pricing.
  - Remove items from the inventory.

- **Order Handling:**
  - Save new orders with customer and item details.
  - Retrieve order history and status.
 
## Benefits

- **Efficient Management:** Streamlines customer and item management with CRUD operations, improving operational efficiency.
- **Robust Order Handling:** Facilitates accurate and reliable order processing, enhancing sales and inventory tracking.
- **Reliable Data Storage:** Uses Java and JDBC for stable data management and persistence.
- **Scalable Architecture:** Built on Java and Tomcat, allowing for easy scaling as business needs grow.

## Installation
To install and run the System, follow these steps:

1. Clone the repository:
   
   ```bash
   git clone https://github.com/jlokitha/POS-System-Backend.git

2. Navigate to the project directory:
   
   ```bash
   cd POS-System-Backend
   
3. Open the project in IntelliJ IDEA:
   
   ```bash
   idea .
## Setup and Configuration
- **Configure Tomcat:**
    - Go to `Run` > `Edit Configurations`.
    - Click the `+` button and select `Tomcat Server` > `Local`.
    - Set the Tomcat Home directory to your [Tomcat](https://tomcat.apache.org/download-90.cgi) installation path.
    - Apply and save the configuration.

- **Update Database Credentials:**
    - Open the `src/main/webapp/META-INF/context.xml` file.
    - Update the MySQL username and password:
  
      ```bash
      <Context>
        <Resource name="jdbc/POSSystem" auth="Container" type="javax.sql.DataSource"
              maxTotal="20" maxIdle="10" maxWaitMillis="-1"
              username="YOUR_USERNAME" password="YOUR_PASSWORD" driverClassName="com.mysql.cj.jdbc.Driver"
              url="jdbc:mysql://localhost:3306/pos_system"/>
      </Context>

- **Run Tomcat:**
    - In IntelliJ IDEA, select the Tomcat configuration you created.
    - Click the `Run` button (green play icon) to start Tomcat.
    - The application should be deployed and accessible from your browser.

## Front-End Project
For the front-end of the POS system, visit the [POS System Front-End Repository](https://github.com/jlokitha/POS-System-Frontend.git).

## API Documentation
You can find the API documentation for this project at [API Documentation](https://documenter.getpostman.com/view/35384124/2sA3s1psRe).

## License
This project is licensed under the MIT License - see the [MIT License](LICENSE) file for details.

##
<div align="center">
<a href="https://github.com/jlokitha" target="_blank"><img src = "https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=github&logoColor=white"></a>
<a href="https://git-scm.com/" target="_blank"><img src = "https://img.shields.io/badge/Git-000000?style=for-the-badge&logo=git&logoColor=white"></a>
<a href="https://jakarta.ee/compatibility/download/" target="_blank"><img src = "https://img.shields.io/badge/JakarthaEE-000000?style=for-the-badge&logo=eclipse&logoColor=white"></a>
<a href="https://www.mysql.com/downloads/" target="_blank"><img src = "https://img.shields.io/badge/MySQL-000000?style=for-the-badge&logo=mysql&logoColor=white"></a>
<a href="https://maven.apache.org/download.cgi" target="_blank"><img src = "https://img.shields.io/badge/Maven-000000?style=for-the-badge&logo=apachemaven&logoColor=white"></a>
<a href="https://tomcat.apache.org/download-90.cgi" target="_blank"><img src = "https://img.shields.io/badge/Tomcat-000000?style=for-the-badge&logo=apachetomcat&logoColor=white"></a>
<a href="https://www.postman.com/downloads/" target="_blank"><img src = "https://img.shields.io/badge/Postman-000000?style=for-the-badge&logo=Postman&logoColor=white"></a>
<a href="https://www.jetbrains.com/idea/download/?section=windows" target="_blank"><img src = "https://img.shields.io/badge/intellij-000000?style=for-the-badge&logo=intellijidea&logoColor=white"></a>
</div> <br>
<p align="center">
  &copy; 2024 Janindu Lokitha
</p>
