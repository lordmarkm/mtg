<#list posts as post>
  <div>
    <div><strong>${post.title }</strong></div>
    <p>${post.text }
    <div>
      <span class="pull-right fromNow">${post.postdate }</span>
    </div>
  </div>
</#list>

<script>
$(function(){
	$('.fromNow').each(function(i, span){
		span.text(moment(span.text()).fromNow()).removeClass('fromNow');
	});
});
</script>