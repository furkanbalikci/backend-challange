
# simple-crud-app

CRUD işlemlerinin yapılabildiği Java Spring kullanılarak yazılan bir web servis uygulamasıdır. Proje özelinde veritabanında 2 tablo bulunmaktadır.
Bu tablolardan biri çalışan(employee) diğeri şirket(company) verilerini tutar. 


## Tech Stack

**Server:** Java 17, Spring Boot 3.0.4 , Spring Data JPA 
**Database**: PostgreSQL 14

## Run Locally

Clone the project

```bash
  git clone https://github.com/furkanbalikci/backend-challange.git
```

Go to the project directory

```bash
  cd backend-challange/simple-crud-project
```

Start the server

```bash
  ./mvnw spring-boot:run
```

## API Reference

### Employee Model Body(#employee-model)

| Feature    | Type              |
|------------|-------------------|
| id         | Long              |
| name       | String            |
| companyId  | Long              |
| department | String            |
| gender     | EnumType.STRING (Male or Female) |
| age        | Integer           |


### **Not:** Id requestbody içerisinde gönderilmemeli.



#### Get all employees

```http
  GET /employee/getAll
```
Tüm çalışanların bilgilerini döner.

#### Get employee by id

```http
  GET /employee/getEmployeeById/{id}
```

Verilen çalışan Id'sine göre çalışan bilgilerini döner. 

#### Get employee by id

```http
  GET /employee/getEmployeesByCompanyName/{companyName}
```

Verilen şirket ismine göre o şirkete kayıtlı olan çalışanların listesini döner.

#### Save employee

```http
  POST /employee/save
```

| Request Parameter | Type     | Example                          |
|:------------------| :------- |:---------------------------------|
| `companyName`     | `string` | /employee/save?companyName=Enoca |

| Request Body | Type       | Description                            |
|:-------------|:-----------|:---------------------------------------|
| `employee`   | `Employee` | [Employee Model Body](###employee-model-body) |

Verilen çalışan bilgilerini veritabanına kaydeder. 


#### Save all employees

```http
  POST /employee/saveAll
```

| Request Body | Type             | Description                            |
|:-------------|:-----------------|:---------------------------------------|
| `employee`   | `List<Employee>` | [Employee Model Body](#employee-model) |

Verilen çalışan bilgilerini toplu şekilde veritabanına kaydeder.

#### Update employee

```http
  PUT /employee/update/{id}
```

| Request Parameter | Type     | Example                          |
|:------------------| :------- |:---------------------------------|
| `companyName`     | `string` | /employee/save?companyName=Enoca |

| Request Body | Type       | Description                            |
|:-------------|:-----------|:---------------------------------------|
| `employee`   | `Employee` | [Employee Model Body](#employee-model) |

Verilen çalışan Id'sine göre çalışan bilgilerini veritabanıda günceller.


#### Delete employee

```http
  DELETE /employee/delete/{id}
```

Verilen çalışan Id'sine göre çalışan bilgilerini veritabanından siler. 




### Company Model Body(#company-model)

| Feature           | Type              |
|-------------------|-------------------|
| id                | Long              |
| name              | String            |
| numberOfEmployees | Long              |
| industries        | String            |


**Not:** Id requestbody içerisinde gönderilmemeli.



#### Get all employees

```http
  GET /company/getAll
```
Tüm şirketlerin bilgilerini döner.

#### Get employee by id

```http
  GET /company/getCompanyById/{id}
```

Verilen şirket Id'sine göre şirket bilgisini döner.



#### Save employee

```http
  POST /company/save
```

| Request Body | Type      | Description                          |
|:-------------|:----------|:-------------------------------------|
| `company`    | `Company` | [Company Model Body](#company-model) |

Verilen şirket bilgilerini veritabanına kaydeder.


#### Save all employees

```http
  POST /company/saveAll
```

| Request Body | Type            | Description                            |
|:-------------|:----------------|:---------------------------------------|
| `company`    | `List<Company>` | [Company Model Body](#company-model) |

Verilen şirket bilgilerini toplu şekilde veritabanına kaydeder.

#### Update employee

```http
  PUT /company/update/{id}
```


| Request Body | Type       | Description                            |
|:-------------|:-----------|:---------------------------------------|
| `company`    | `Company` | [Company Model Body](#company-model) |

Verilen şirket Id'sine göre şirket bilgilerini veritabanıda günceller.


#### Delete employee

```http
  DELETE /company/delete/{id}
```

Verilen şirket Id'sine göre şirket bilgilerini veritabanından siler.



