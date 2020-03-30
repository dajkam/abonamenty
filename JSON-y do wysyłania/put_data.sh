#!/bin/bash

# plik dodający jsony z danymi startowymi

#  w ponizszej linijce jest dodawanie marki pojazdu z zalogowaniem się za pomocą loginu i hasła

# curl -u user:du -X PUT -d @mercedes.json http://localhost:8091/marka/insert -H "Content-Type: application/json" 

cd marki

curl -u user:du -X PUT -d @mercedes.json http://localhost:8091/marka/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @Volksvagen.json http://localhost:8091/marka/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @toyota.json http://localhost:8091/marka/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @Maybach.json http://localhost:8091/marka/insert -H "Content-Type: application/json"



cd ..

cd modele

curl -u user:du -X PUT -d @CKlasa.json http://localhost:8091/model/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @AKlasa.json http://localhost:8091/model/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @SKlasa.json http://localhost:8091/model/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @garbus.json http://localhost:8091/model/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @golf2.json http://localhost:8091/model/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @Yaris.json http://localhost:8091/model/insert -H "Content-Type: application/json"

cd ..

cd obywatele 

curl -u user:du -X PUT -d @filip.json http://localhost:8091/obywatel/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @jarek.json http://localhost:8091/obywatel/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @jurek.json http://localhost:8091/obywatel/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @marek.json http://localhost:8091/obywatel/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @irek.json http://localhost:8091/obywatel/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @agata.json http://localhost:8091/obywatel/insert -H "Content-Type: application/json"



cd ..

cd pojazdy 

curl -u user:du -X PUT -d @poj1.json http://localhost:8091/pojazd/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @poj2.json http://localhost:8091/pojazd/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @poj3.json http://localhost:8091/pojazd/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @poj4.json http://localhost:8091/pojazd/insert -H "Content-Type: application/json"

cd .. 

cd abonamenty 

curl -u user:du -X PUT -d @ab1.json http://localhost:8091/abonament/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @ab2.json http://localhost:8091/abonament/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @ab3.json http://localhost:8091/abonament/insert -H "Content-Type: application/json"
curl -u user:du -X PUT -d @ab4.json http://localhost:8091/abonament/insert -H "Content-Type: application/json"

cd ..


