$(function() {
  
  var page = 0, size = 5;
  var 
   $loadhere = $('#posts-loadhere'),
   $btnLoad = $('#btn-loadmore');
  
  function loadmore() {
    $btnLoad.attr('disabled', 'disabled').addClass('disabled');
    loadhere.loading();
    $.get(postableUrls.load, {page: page, size: size}, function(response) {
      $loadhere.append(response);
      loadhere.notloading();
    }).complete(function(){
    	$btnLoad.removeAttr('disabled').removeClass('disabled');
    });
  }
  
  $btnLoad.click(function(){
    page += 1;
    loadmore();
  });
  
  loadmore(); //first time
  
});