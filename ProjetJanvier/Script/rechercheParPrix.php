
<?php
	header('Content-type: application/json');

	try
	{
		$bdd = new PDO('mysql:host=127.0.0.1;port=3306;dbname=projetjanvier;charset=utf8', 'root', '');
	}
	catch(Exception $e)
	{
		die('Erreur : '.$e->getMessage());
	}

	$prixMin = floatval($_GET['prixMin']);
	$prixMax = floatval($_GET['prixMax']);
	$items = array();

	$sql = "SELECT * FROM article WHERE prix BETWEEN ".$prixMin." AND ".$prixMax;

	foreach($bdd->query($sql) as $row){
		$temp = [
			'nom'=>$row['Nom'],
			'prix'=>$row['Prix'],
			'ville'=>$row['Ville'],
			'etat'=>$row['Etat'],
			'desc'=>$row['Description'],
			'info'=>$row['Info']
		];
		array_push($items,$temp);
	}

	echo json_encode($items);
?>
