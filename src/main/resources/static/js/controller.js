var app = angular.module('app', [])

app.controller('controller', function($scope, $filter, $http) {
    $scope.data = {
        requestType : null,
        requestTypes : [
            {value : 'html', name : 'HTML'},
            {value : 'text', name : 'TEXT'}
        ]
    };
    $scope.requestUrl = '';
    $scope.quotientStr = '';
    $scope.remainStr = '';
    $scope.divisionFactor = '1';

    $scope.clickSend = function() {
        var path = '/crawlers';
        console.log('' + $scope.data.requestType);
        if ($scope.data.requestType === 'html') {
            path += '/html';
        }
        else if ($scope.data.requestType === 'text') {
            path += '/text';
        }
        var url = path + "?url=" + $scope.requestUrl + "&divisionFactor=" + $scope.divisionFactor;
        console.log("url : " + url);
        $http({
            method : 'GET',
            url : url,
            header : {'ContentType' : 'application/json; charset=utf-8'}
        }).success(function(data, status, header, config) {
            data.result = data;
            $scope.quotientStr = data.result.quotientStr;
            $scope.remainStr = data.result.remainStr;
        }).error(function (data, status, header, config) {
            alert(data.message);
        })
    }
});