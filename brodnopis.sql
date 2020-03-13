select * from obywatel
        where 
        lower(cast(id as varchar(36))) like '%ja%' or lower(pesel) like '%ja%' or lower(nr_dowodu) like '%ja%'
        or lower(imie) like '%ja%' or lower(nazwisko) like '%ja%' or lower(cast(data_urodzenia as varchar(36))) like '%ja%'
        or lower(adres) like '%ja%'
