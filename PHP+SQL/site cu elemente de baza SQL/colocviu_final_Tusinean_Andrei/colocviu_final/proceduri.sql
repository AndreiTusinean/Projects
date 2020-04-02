

--procedura care returneaza numarul cardului cu limita maxima
DROP PROCEDURE IF EXISTS max_lim;
DELIMITER $$
CREATE  PROCEDURE `max_lim`()
BEGIN
SELECT nrcard,limita 
FROM Carti_de_credit
WHERE limita >= ALL(SELECT limita FROM Carti_de_credit);
END;
