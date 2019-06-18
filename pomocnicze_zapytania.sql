select * from abonament where kiedy_utworzono=( select max(kiedy_utworzono) from abonament); //znajdz najnowszą datę w abonamencie

select * from abonament where id=( select max(id) from abonament);
