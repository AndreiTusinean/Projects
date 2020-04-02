<html>
<head>
<title>3_a</title>
</head>
<body background="img-13.jpg">
<p ><font size=6>Subiectul 8</p> </font>
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

<p align=left><font type=cortana size=5>8.03  Să se exprime în SQL urmatoarele interogari:</font></p> </br>
    <font size=4>a) Sa se gaseasca persoanele nascute in anul 1980 in ordinea datei nasterii.</font>
 
<form action="3_a.php" method=post>   
      
<input type=submit value="Afiseaza" name="a"> 
    
</form></br></br></br>

<?php
include 'db_connection.php';
$conn = OpenCon();

if(isset($_POST['a']))
{	
$sql = "SELECT nume,data_nasterii "

    . "FROM Persoane \n"

    . "WHERE data_nasterii BETWEEN '1980-01-01'AND '1980-12-31'\n"

    . "ORDER BY data_nasterii";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 
echo"<table  border='1' bgcolor=white>";
	echo"<tr><td>nume</td><td>data_nasterii</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['nume']}</td><td>{$item['data_nasterii']}</td></tr><br>";
}
CloseCon($conn);
}
?>
</body>
</html>