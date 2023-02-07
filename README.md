# Medical Clinic Assignment 
## Overview
Medical clinic with one doctor, needs appointment management system, where
clinic admin can review appointments by date or patient

*Unfortunately, I haven't finished it yet, because I ran out of time for 24h time limit, I created most of the functionalities, based on the User Stories

## Running the Application
- Clone The Repo
  ```git clone https://github.com/ah3nan/medical-clinic```

- Then
  ``cd medical-clinic && docker compose up``
- Create the database
``sudo docker exec -it postgres-clinic bash`` and
``psql -U postgres`` and
`create database medical_clinic;`
- Open new terminal and restart the medicalclinic container then
- Open in-browser
  ``http://localhost:8081/api/v1/swagger-ui/index.html``
