# HR-SYSTEM
* [Project Description](#project-description)
* [Project Features](#project-features)
* [Roles](#roles)
* [Guest's scope](#guests-scope)
* [Applicant's scope](#applicants-scope)
* [Employee's scope](#employees-scope)
* [Admin's scope](#admins-scope)
* [Additional Info](#additional-info)

# Project Description
HR-SYSTEM allows employees to hire new people. Employees create and manage vacancies. Also, employees manage applicants’ requests (create interview results or schedule a technical interview). Guests can see only vacancies. To apply for the selected vacancy guest needs to create an account. After that guest turns into the applicant. Applicant fills applicant request form and applies for the selected vacancy. Then applicant passes several interviews and waits for the results.

# Project Features
* Localization: en_EN, ru_RU
* 4 user's roles
* Used Bootstrap
* XSS protection with custom tag
* Custom connection pool and proxy connections
* Double data validation

# Roles
* Guest
* Applicant
* Employee
* Admin

# Guest's scope
* Register
* Login
* Change language
* View vacancies

# Applicant's scope
* Create applicant request and apply for the selected vacancy
* View applicant own requests

# Employee's scope
* Manage vacancy (CRUD)
* View applicants requests
* View applicants profiles
* Create interview result
* Schedule a technical interview

# Admin's scope
commands

# Authorized user's scope
* Update profile
* Change avatar

# Additional Info
* Default users password: `password`
* Don't forget to enter email address and password in the properties file (`mail.properties`) for correct email sending
 ```java
mail.user.name=yourEmail
mail.user.password=yourPassword
