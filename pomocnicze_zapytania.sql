select * from abonament where kiedy_utworzono=( select max(kiedy_utworzono) from abonament); //znajdz najnowszą datę w abonamencie

select * from abonament where id=( select max(id) from abonament); /// alternatywa dla powyższego

select * from obywatel where czy_zarchiwizowany = false


/////// joiny ///////////



select * from pojazd
join model 
on pojazd.model_id = model.id
join marka
on model.marka_id = marka.id


////////pojazd.///////////join do pobierania informacji potrzebnych do wyświetlania pojazdu na stronie ///////////////////////////////

select pojazd.id,pojazd.czy_zarchiwizowany,pojazd.kiedy_utworzono,pojazd.kiedy_zmodyfikowano,pojazd.kolor,pojazd.nr_rejstracyjny_pojazdu,pojazd.rok_produkcji,pojazd.uwagi model.nazwa,marka.nazwa from pojazd
  join model 
on pojazd.model_id = model.id
 join marka
on model.marka_id = marka.id 




////////// to samo co powyrzej tylko z dodanymi alliasami dla nazwy marki i nazwy modelu pojazdu////////////

select pojazd.id,pojazd.czy_zarchiwizowany,pojazd.kiedy_utworzono,pojazd.kiedy_zmodyfikowano,pojazd.kolor,pojazd.nr_rejstracyjny_pojazdu,pojazd.rok_produkcji,pojazd.uwagi, marka.nazwa as marka,model.nazwa as model from pojazd
  join model 
on pojazd.model_id = model.id
  join marka
on model.marka_id = marka.id

///////////////// to samo co powyrzej tylko z pomocniczymi polami

select pojazd.id,pojazd.czy_zarchiwizowany,pojazd.kiedy_utworzono,pojazd.kiedy_zmodyfikowano,pojazd.kolor,pojazd.nr_rejstracyjny_pojazdu,pojazd.rok_produkcji,pojazd.uwagi, marka.nazwa as marka,model.nazwa as model, pojazd.model_id,pojazd.obywatel_id, model.marka_id from pojazd
  join model 
on pojazd.model_id = model.id
  join marka
on model.marka_id = marka.id

/////////// widok abonament - pojazd obywatel // bez modelu i marki pojazdu


select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, pojazd.nr_rejstracyjny_pojazdu, obywatel.imie, obywatel.nazwisko, obywatel.pesel from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id


/////////// widok abonament - pojazd obywatel // z modelu i marki pojazdu




select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor,abonament.czy_zarchiwizowany, pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko, obywatel.pesel from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id


/// to samo z kluczami obcymi

select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id
      

/// to samo tylko z kedy utworzono i kiedy zmodyfikowano   /// brak było czy_zarchiwizowane dlatego sie nie wyświetlałó

select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id






//// to samo tylko jeszcze z abonament.uwagi

 select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany, abonament.uwagi,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id

//// to samo tylko jeszcze z like 

 select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany, abonament.uwagi,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id
where obywatel.imie like '%p%' or nr_rejstracyjny_pojazdu like '%p%'

//// to samo tylko jeszcze z like i zprowadzeniem wszystkiego do lower case

 select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany, abonament.uwagi,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id
where lower(obywatel.imie) like '%a%' or lower(pojazd.nr_rejstracyjny_pojazdu) like '%p%'


//// to samo tylko jeszcze z like pełne do wklejenia jako native query do programu

 select abonament.id, abonament.data_rozpoczecia, abonament.data_zakonczenia, abonament.sektor, abonament.czy_zarchiwizowany, abonament.uwagi,
 pojazd.nr_rejstracyjny_pojazdu, marka.nazwa as marka,model.nazwa as model, obywatel.imie, obywatel.nazwisko,
  obywatel.pesel, abonament.pojazd_id, pojazd.obywatel_id, model.marka_id, pojazd.model_id, abonament.kiedy_utworzono, abonament.kiedy_zmodyfikowano from abonament
    join pojazd 
on abonament.pojazd_id = pojazd.id
    join obywatel 
on pojazd.obywatel_id = obywatel.id
    join model 
on pojazd.model_id = model.id
    join marka
on model.marka_id = marka.id
where  
 lower(abonament.sektor) like '%p%'
or lower(pojazd.nr_rejstracyjny_pojazdu) like '%p%' or lower(marka.nazwa) like '%p%' 
or lower(model.nazwa) like '%p%' or lower(obywatel.imie) like '%a%' 
or lower(obywatel.nazwisko) like '%a%' or lower(obywatel.pesel) like '%a%'

archiwizacja obywatela 

update obywatel 
set czy_zarchiwizowany = true 
where id = 3
;

// wyszukiwanie pojazdu


 select pojazd.id,pojazd.czy_zarchiwizowany,pojazd.kiedy_utworzono,pojazd.kiedy_zmodyfikowano,pojazd.kolor,pojazd.nr_rejstracyjny_pojazdu,pojazd.rok_produkcji,pojazd.uwagi, marka.nazwa as marka,model.nazwa as model, pojazd.model_id,pojazd.obywatel_id, model.marka_id from pojazd
  join model 
on pojazd.model_id = model.id
  join marka
on model.marka_id = marka.id
        where 
        lower(pojazd.nr_rejstracyjny_pojazdu) like ?1 or lower(marka.nazwa) like ?1 or lower(model.nazwa) like ?1
        or lower(cast(pojazd.rok_produkcji as varchar(36) )) like ?1 or lower(pojazd.kolor) like ?1
        or lower(pojazd.uwagi) like ?1 or lower(cast(pojazd.id as varchar(36))) like ?1
      

// dodanie usera 

INSERT INTO user (imie,
        nazwisko,
        email,
        haslo,
        role,
        czy_zarchiwizowany ,
        aktywne,
        nie_wygasniete,
        nie_zablokowane,
        nie_wygasniete_id_i_haslo,
        kiedy_utworzono ,
       kiedy_zmodyfikowano,
       dtype
 ) VALUES ('Filip','Machaj', 'machajfil@gmail.com','123fil','ULICZNY,STRAZNIK,ADMIN,',
false,true,true,true,true,'2008-01-01 00:00:01','2008-01-01 00:00:01','fdrgrg');




