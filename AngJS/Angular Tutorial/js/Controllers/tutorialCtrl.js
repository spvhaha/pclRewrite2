angular.module("tutorialCtrlModule",[])

.controller("TutorialCtrl",["$scope",function($scope) {

    $scope.tutorialObject = {};
    $scope.tutorialObject.title ="Main Page" ;
    $scope.tutorialObject.subTitle ="Sub title";
    $scope.tutorialObject.bindOutput = 4;
    $scope.tutorialObject.firstname= "Shital";
    $scope.tutorialObject.lastname= "Kansara";
    $scope.timesTwo =function(){
         $scope.tutorialObject.bindOutput *=2;
    }
}])

    .controller("TutorialCtrl2", ["$scope", function($scope) {
        $scope.secondTutorial = "This is the second tutorial !";
        }]);
