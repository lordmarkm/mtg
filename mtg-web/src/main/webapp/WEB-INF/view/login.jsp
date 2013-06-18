<#import "/spring.ftl" as spring /> 

<#assign action><@spring.url '/login/authenticate' /></#assign>
<form class="form-horizontal" action="${action }" method="post">
  <fieldset>
    <legend>Login</legend>

    <#if message??>
    <#switch message>
      <#case 'signout_success'>
    <div class="alert alert-success">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      Log out successful.
    </div>
        <#break>
    
    
      <#case 'regsuccess'>
    <div class="alert alert-success">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      Registration successful. You can now Log in.
    </div>
        <#break>
    
    
      <#case 'bad_credentials'>
    <div class="alert alert-error">
      <button type="button" class="close" data-dismiss="alert">&times;</button>
      Incorrect username or password
    </div>
        <#break>
    </#switch>
    </#if>
    
    <div class="control-group">
      <label class="control-label" for="username">Username</label>
      <div class="controls">
        <input type="text" name="username" />
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="password">Password</label>
      <div class="controls">
        <input type="password" name="password" />
      </div>
    </div>
  </fieldset>
  <div class="control-group">
    <div class="controls">
      <input class="btn btn-primary" type="submit" value="Log in" />
    </div>
  </div>    
</form>
<p>No account? <a href="<@spring.url '/auth/register' />">Sign up!</a>
