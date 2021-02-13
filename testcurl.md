curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"email":"olivier.barais@gmail.com","name":"Barais", "firstname" : "Olivier" }' \
  http://localhost:8080/api/user


curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/api/user/olivier.barais@gmail.com


curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"iduser":"olivier.barais@gmail.com","nom":"Urgence"}' \
  http://localhost:8080/api/listtache


curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/api/listtache/olivier.barais@gmail.com





curl --header "Content-Type: application/json" \
  --request POST \
  --data '{"idListTache":"1","intitule":"Finir cours TAA"}' \
  http://localhost:8080/api/tache


curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/api/tache/olivier.barais@gmail.com


