<html>
<head>
<title>5_a</title>
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

<p align=left><font type=cortana size=5>8.05  Să se exprime în SQL fara functii de agregare urmatoarele interogari folosind cel putin o interogare imbricata si operatori de genul EXISTS,IN,ALL,ANY:</font></p> </br>
    <font size=4>a) Sa se gaseasca numele persoanelor ce detin mai multe carti de credit.</font>
 
<form action="5_a.php" method=post>   
      
<input type=submit value="Afiseaza" name="a"> 
    
</form></br></br></br>

<?php
include 'db_connection.php';

$conn = OpenCon();
if(isset($_POST['a']))
{
$sql = "SELECT DISTINCT p.nume \n"

    . "FROM Carti_de_credit cc \n"

    . "INNER JOIN Carti_de_credit cc2 ON cc.nrcont=cc2.nrcont AND cc.nrcard!=cc2.nrcard AND cc.nrcard<cc2. nrcard\n"

    . "INNER JOIN Conturi cn ON cc.nrcont=cn.nrcont \n"

    . "INNER JOIN Persoane p ON p.idpers=cn.idpers\n"

    . "WHERE EXISTS(SELECT nume FROM Persoane WHERE p.idpers=cn.idpers)\n"

    . "GROUP BY p.nume";
	

$result = mysqli_query($conn,$sql) or die("<br>Bad Query: $sql"); 

echo"<table border='2' bgcolor=white>";
echo"<tr><td>nume</td></tr>";
foreach($result as $item){
	echo "<tr><td>{$item['nume']}</td></tr>";
}
CloseCon($conn);
}
?>
</body>
</html>