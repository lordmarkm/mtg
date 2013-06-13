<#import "/spring.ftl" as spring /> 

<div class="clearfix"></div>
<div class="container">
  <div class="span8">
    <#assign action><@spring.url '/login/authenticate' /></#assign>
    <form action="${action }" method="post">
      <fieldset>
        <legend>Login</legend>
        <ul class="nobullets">
          <li><label>Username: <input type="text" name="username" /></label></li>
          <li><label>Password: <input type="password" name="password" /></label></li>
        </ul>
      </fieldset>
      <input type="submit" />
    </form>
  </div>
</div>