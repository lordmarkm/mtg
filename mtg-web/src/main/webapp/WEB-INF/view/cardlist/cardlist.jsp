<#import "/spring.ftl" as spring />

<!doctype html>
<html lang="en" data-ng-app="cardlistApp">

<head>

  <script>
  var vars = {
		username : '${username}'
  };
  </script>

  <meta charset="utf-8">
  <title>Cardlist</title>
  <script src="<@spring.url '/libs/backups/jquery.min.js'            />"></script>
  <script src="<@spring.url '/libs/backups/angular.min.js'           />"></script>
  <script src="<@spring.url '/javascript/cardlist/cardlistcontroller.js' />"></script>

</head>

<body data-ng-controller="CardListCtrl">
  <p>Nothing here {{'yet' + '!'}}</p>
  <p>1 + 2 = {{ 1 + 2 }}</p>
  
  
  <div>
    Search: <input data-ng-model="query" />
  </div>
  
  <ul>
    <li data-ng-repeat="bundle in bundles | filter: query">
      {{bundle.cardName}} - {{bundle.count}} -  {{bundle.note}}
    </li>
  </ul>
  
</body>

</html>