<html>
<head>
<title>6_b</title>
</head>
<body background="img-13.jpg">
<p ><font align="center" size=6></p>Subiectul 8.</font>	
<table bgcolor="#FFFFFF" border =1>
<tr>
  <th><a href="index.php">Index Page</a></th>
  <th><a href="3_a.php">Exercitiul 3 a)</a></th>
  <th><a href="3_b.php">Exercitiul 3 b)</a></th>
  <th><a href="4_a.php">Exercitiul 4 a)</a></th>
  <th><a href="4_b.php">Exercitiul 4 b)</a></th>
  <th><a href="5_a.php">Exercitiul 5 a)</a></th>
  <th><a href="5_b.php">Exercitiul 5 b)</a></th>
  <th><a href="6_a.php">Exercitiul 6 a)</a></th>
  <th><a href="6_b.php">Exercitiul 6 b)</a></th>
</tr>
</table>

<div class=container align=center>

<p align=left><font type=cortana size=5>8.06  Să se exprime în SQL următoarele interogări folosind funcţii de agregare:</font></p> </br>
    <font size=4>b) Sa se gaseasca pentru cartea de credit cu numarul 123456 suma valorii cheltuite in anul 2018.</font>
 
<form action="6_b.php" method=post>   
      
<input type=submit value="Afiseaza" name="b"> 
    
</form></br></br></br>


<?php
include 'db_connection.php';

$conn = OpenCon();
if(isset($_POST['b']))
{
$sql = "SELECT SUM(valoare) AS 'a'\n"

    . "FROM Miscari \n"

    . "WHERE nrcard=123456 AND data_ora BETWEEN '2018-01-01' AND '2018-12-31' ";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

	echo"<table border='2' bgcolor=white>";
	echo "<tr><td>Suma cheltuita</td></tr>";
	foreach($result as $item){
		echo "<tr><td>{$item['a']}</td></tr>";
	}
CloseCon($conn);
}
?>
</body>
</html>