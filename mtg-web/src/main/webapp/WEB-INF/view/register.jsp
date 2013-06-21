<#import "/spring.ftl" as spring /> 

<#assign action><@spring.url '/auth/register' /></#assign>
<form id="registration-form" class="form-horizontal" action="${action }" method="post">
  <fieldset>
    <legend>Register</legend>
    
    <div class="control-group">
      <label class="control-label" for="username">Username</label>
      <div class="controls">
        <input type="text" name="username" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="email">Email</label>
      <div class="controls">
        <input type="email" name="email" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" name="password" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="password">Confirm Password</label>
      <div class="controls">
        <input type="password" name="confirmpw" />
      </div>
    </div>
  </fieldset>
  <div class="control-group">
    <div class="controls">
      <input class="btn btn-success btn-large" type="submit" value="Sign Up" />
    </div>
  </div>
</form>
<p>Already have an account? <a href="<@spring.url '/auth/login' />">Log in</a>

<script>
$(function(){
	var regurls = {
		login : '<@spring.url "/auth/login/regsuccess" />'	
	}
	
	var $form = $('#registration-form');
	
	$form.submit(function(){
		var url = $form.attr('action');
		
		$.post(url, $form.serialize(), function(response) {
			switch(response.status) {
			case '200':
				window.location.replace(regurls.login);
				break;
			default:
				footer.error(response.message);
			}
		});
		
		return false;
	});
});
</script>
