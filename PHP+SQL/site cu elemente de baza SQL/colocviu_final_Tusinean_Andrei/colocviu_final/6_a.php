<html>
<head>
<title>6_a</title>
</head>
<body background="img-13.jpg">
<p ><font size=6></p>Subiectul 8</font>
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

<p align=left><font type=cortana size=5>8.06  Să se exprime în SQL următoarele interogări folosind funcţii de agregare:</font></p> </br>
    <font size=4>a) Sa se gaseasca pentru fiecare persoana cate carti de credit exista.</font>
 
<form action="6_a.php" method=post>   
      
<input type=submit value="Afiseaza" name="a"> 
    
</form></br></br></br>

<?php
include 'db_connection.php';

$conn = OpenCon();
if(isset($_POST['a']))
{
$sql = "SELECT COUNT(nrcard) \"Numar de carduri\",p.nume\n"

    . "FROM Persoane p \n"

    . "INNER JOIN Conturi cn ON cn.idpers=p.idpers \n"

    . "INNER JOIN Carti_de_credit cc ON cc.nrcont=cn.nrcont \n"

    . "GROUP BY p.nume";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

	echo"<table border='2' bgcolor=white>";
	echo "<tr><td>Numar de carduri</td><td>Nume</td></tr>";
	foreach($result as $item){
		echo "<tr><td>{$item['Numar de carduri']}</td><td>{$item['nume']}</td></tr>";
	}
CloseCon($conn);
}
?>
</body>
</html>