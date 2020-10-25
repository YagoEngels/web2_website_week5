<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>speler toevoegen</title>
</head>
<body class="body">
<div class="bg"></div>
<header>
    <h1>ADD PLAYER</h1>
</header>
<nav>
    <ul>
        <li><a href="index.html" >HOME</a></li>
        <li><a href="tweedePag.html" class="kleur">ADD PLAYER</a></li>
        <li><a href="servlet?task=overview">PLAYER LIST</a></li>
    </ul>
</nav>
<article>
    <h2>find player</h2>
    <form action="servlet?task=find" method="POST">
        <fieldset class="formulier">
            <legend>find player</legend>
            <label for="naam">naam :</label>
            <input type="text" name="naam" id="naam" required="">
            <input type="submit" value="FIND PLAYER">
        </fieldset>
    </form>
</article>
</body>
</html>