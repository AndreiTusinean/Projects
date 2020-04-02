<html>
<head>
<title>4_a</title>
</head>
<body background="img-13.jpg">
<p ><font size=6>Subiectul 8</p></font>
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
<p align=left><font type=cortana size=5>8.04  Să se exprime în SQL urmatoarele interogari folosind operatorul JOIN:</font></p> </br>
    <font size=4>a) Cine este titularul cartii de credit cu nrcard = 123456?</font>
	<form action="4_a.php" method=post> 
    
<table border=1 bgcolor=white>
    
<tr>
<td>Introduceti numarul cardului:</td>
<td align="center"><input type="text" name="a" size="5" maxlength="6"></td>
</tr>    
    
<tr>
<td colspan="2" align="center"><input type=submit value="Afiseaza"></td>
</tr>
    
</table>  
    
<?php
include 'db_connection.php';

if(isset($_POST['a']))
{
	$a=$_POST['a'];
$conn = OpenCon();
$sql = "SELECT nume \n"

    . "FROM Persoane\n"

    . "WHERE idpers=(SELECT cn.idpers FROM\n"

    . "Conturi cn INNER JOIN Carti_de_credit c \n"

    . "ON cn.nrcont=c.nrcont AND c.nrcard=$a)";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo "<tr><td>nume</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['nume']}</td></tr><br>";
}
CloseCon($conn);
}
?>
</body>
</html>