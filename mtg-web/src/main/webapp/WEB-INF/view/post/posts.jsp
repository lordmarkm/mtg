<#import "../templates/tools.ftl" as tools />

<#list posts as post>
  <div class="post-container">
    <div><strong>${post.title }</strong></div>
    <p><@tools.nl2br string=post.text />
    <div>
      <span class="pull-right fromNow">${post.postdate }</span>
    </div>
    <div class="clearfix"></div>
  </div>
</#list>

<script>
$(function(){
	if($('.fromNow').length === 0) {
		$('#btn-loadmore').hide();
		return;
	}
	
	$('.fromNow').each(function(i, span){
		var $span = $(span);
		$span.text(moment($span.text()).fromNow()).removeClass('fromNow');
	});
});
</script>