<?php 
	try
	{
		$bdd = new PDO('mysql:host=127.0.0.1;port=3306;dbname=projetjanvier;charset=utf8', 'root', '');
	}
	catch(Exception $e)
	{
	    die('Erreur : '.$e->getMessage());
	}

	$nom = $_POST['nom'];
	$description = $_POST['desc'];
	$prix = $_POST['prix'];
	$etat = $_POST['etat'];
	$ville = $_POST['ville'];
	$info = $_POST['info'];

   	$stmt = $bdd->prepare("INSERT INTO article (Nom, Description, Prix, Etat, Ville, Info) VALUES (:nom, :desc, :prix, :etat, :ville, :info)");
   	$stmt->execute(array('nom' => $nom,
   					'desc' => $description,
   					'prix' => $prix,
   					'etat' => $etat,
   					'ville' => $ville,
   					'info' => $info));
   	$stmt->close();
?>
