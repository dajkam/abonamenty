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
