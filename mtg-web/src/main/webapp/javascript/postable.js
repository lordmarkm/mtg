$(function() {
  
  var page = 0, size = 5;
  var 
   $loadhere = $('#posts-loadhere'),
   $btnLoad = $('#btn-loadmore');
  
  function loadmore() {
    loadhere.loading();
    $.get(postableUrls.load, {page: page, size: size}, function(response) {
      $loadhere.append(response);
      loadhere.notloading();
    });
  }
  
  $btnLoad.click(function(){
    page += 1;
    loadmore();
  });
  
  loadmore(); //first time
  
});