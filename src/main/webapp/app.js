var app = angular.module('myApp', []);


app.controller('controller', function ($scope, $http) {


    $scope.postagemPage = 0;
    $scope.storyPage = 0;
    $scope.postagens = [];
    $scope.curtir = curtir;
    $scope.carregarMaisPostagens = carregarMaisPostagens;
    $scope.postar = postar;
    $scope.comentar = comentar;
    $scope.carregarStory = carregarStory;
    $scope.selecionaUsuario = selecionaUsuario;

    function selecionaUsuario(usuario) {
        $scope.usuario = usuario;
    }
    
    function carregarPostagem() {
        $http({
            method: "GET",
            url: "api/feed?page=" + $scope.postagemPage + "&limit=10",
        }).then(function mySuccess(response) {
            $scope.postagens = $scope.postagens.concat(response.data);
            for (var x = 0; x < response.data.length; x++) {
                carregarComentario(response.data[x]);
            }
        }, function myError(response) {
            $scope.postagens = [];
        });
    }

    function carregarStory() {
        $http({
            method: "GET",
            url: "api/story?page=" + $scope.storyPage + "&limit=1",
        }).then(function mySuccess(response) {
            $scope.stories = response.data;
        }, function myError(response) {
            $scope.stories = [];
        });
    }

    function carregarJobs() {
        $http({
            method: "GET",
            url: "api/job?page=0&limit=20",
        }).then(function mySuccess(response) {
            $scope.jobs = response.data;
        }, function myError(response) {
            $scope.jobs = [];
        });
    }

    function postar() {
        if ($scope.textoPostagem) {
            var postagem = {usuario: $scope.usuario, conteudo: $scope.textoPostagem};
            $http({
                method: "POST",
                url: "api/feed",
                data: postagem
            }).then(function mySuccess(response) {
                $scope.postagens = [];
                carregarPostagem();
                $scope.textoPostagem = "";
            }, function myError(response) {
                alert("Erro ao postar ", response);
            });
        }
    }

    function carregarMaisPostagens() {
        $scope.postagemPage++;
        carregarPostagem();
    }

    function carregarUsuarios() {
        $http({
            method: "GET",
            url: "api/usuario?page=" + $scope.postagemPage + "&limit=10",
        }).then(function mySuccess(response) {
            $scope.usuarios = response.data;
            $scope.usuario = $scope.usuarios[$scope.usuarios.length - 1]
        }, function myError(response) {
            $scope.usuarios = [];
        });

    }

    function carregarComentario(postagem) {
        $http({
            method: "GET",
            url: "api/comentario?page=" + $scope.postagemPage + "&limit=10&postagemId=" + postagem.id,
        }).then(function mySuccess(response) {
            postagem.comentarios = response.data;
        }, function myError(response) {
            postagem.comentarios = [];
        });
    }


    function comentar(postagem) {
        if (postagem.novoComentario) {
            var comentario = {feed: postagem, usuario: $scope.usuario, conteudo: postagem.novoComentario};
            $http({
                method: "POST",
                url: "api/comentario",
                data: comentario
            }).then(function mySuccess(response) {
                postagem.comentarios.push(response.data);
                postagem.novoComentario = "";
            }, function myError(response) {
                alert("Erro ao comentar ", response);
            });
        }
    }


    function curtir(postagem) {
        var curtida = {feed: postagem, usuario: $scope.usuario};
        $http({
            method: "POST",
            url: "api/curtir",
            data: curtida
        }).then(function mySuccess(response) {
            var index = $scope.postagens.indexOf(postagem);
            if (index >= 0) {
                carregarComentario(response.data)
                $scope.postagens[index] = response.data;
            }
        }, function myError(response) {
            alert("Erro ao curtir ", response);
        });
    }


    carregarUsuarios();
    carregarPostagem();
    carregarStory();
    carregarJobs();


});
