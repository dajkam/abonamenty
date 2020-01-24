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


archiwizacja obywatela 

update obywatel 
set czy_zarchiwizowany = true 
where id = 3
;
      
      


