<html>
<head>
<title>3_b</title>
</head>
<body background="img-13.jpg">
<p ><font size=6>Subiectul 8</p> </font>
<table border =1 bgcolor=white>
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

<p align=left><font type=cortana size=5>8.03  Să se exprime în SQL urmatoarele interogari:</font></p> </br>
    <font size=4>b) Sa se gaseasca detaliile cartilor de credit cu limita intre 5000 sau 7500 sau 10000 ordonat descrescator dupa limita si crescator dupa tip</font>
 
<form action="3_b.php" method=post>   
      
<input type=submit value="Afiseaza" name="b"> 
    
</form>

<?php
include 'db_connection.php';

if(isset($_POST['b']))
{	
$conn = OpenCon();
$sql = "SELECT * \n"

    . "FROM Carti_de_credit \n"

    . "WHERE limita BETWEEN 5000 AND 7500 OR limita BETWEEN 7500 AND 10000 \n"

    . "ORDER BY limita DESC,tip ASC";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo "<tr><td>nrcard</td><td>data_de_la</td><td>data_la</td><td>limita</td><td>nrcont</td><td>tip</td><br>";
foreach($result as $item){
	echo "<tr><td>{$item['nrcard']}</td><td>{$item['data_de_la']}</td><td>{$item['data_la']}</td>
	<td>{$item['limita']}</td><td>{$item['nrcont']}</td><td>{$item['tip']}</td></tr><br>";
}
CloseCon($conn);
}
?>
</body>
</html>