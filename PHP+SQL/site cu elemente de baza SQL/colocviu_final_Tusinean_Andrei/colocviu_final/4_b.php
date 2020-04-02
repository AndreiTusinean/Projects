<html>
<head>
<title>4_b</title>
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
<p align=left><font type=cortana size=5>8.04  Să se exprime în SQL urmatoarele interogari folosind operatorul JOIN:</font></p> </br>
    <font size=4>b) Sa se gaseasca perechi de carduri care apartin aceleasi persoane(idpers,nrcard2,nrcard2).O pereche este unica in rezultat.</font>
 
<form action="4_b.php" method=post>   
      
<input type=submit value="Afiseaza" name="b"> 
    
</form></br></br></br>

<?php
include 'db_connection.php'; 

if(isset($_POST['b']))
{
$conn = OpenCon();
$sql = "SELECT DISTINCT cn.idpers ,cc.nrcard AS 'A',cc2.nrcard AS 'B'\n"

    . "FROM Carti_de_credit cc \n"

    . "INNER JOIN Carti_de_credit cc2 ON cc.nrcont=cc2.nrcont AND cc.nrcard!=cc2.nrcard AND cc.nrcard<cc2. nrcard\n"

    . "INNER JOIN Conturi cn ON cc.nrcont=cn.nrcont \n";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo "<tr><td>Idpers</td><td>nrcard1</td><td>nrcard2</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['idpers']}</td><td>{$item['A']}</td><td>{$item['B']}</td></tr><br>";
}
CloseCon($conn);
}
?>
</body>
</html>