<#import "/spring.ftl" as spring />

<h3>Welcome to Tomescour</h3>

<h4>What is Tomescour &reg;? What can I do here?</h4>
<p>Tomscour &reg; makes your binder dreams come true.

<p>&nbsp;

<h4>I. Browse binders</h4>
<p>From the <a href="<@spring.url '/binders/browse' />">Binders</a> page you can browse all binders
organized by location. This is helpful if you want to know what cards are for trade at a location
near you. 

<p>&nbsp;

<h4>II. Browse cards</h4>
<p>From the <a href="<@spring.url '/cards/browse' />">Cards</a> page you can browse every Magic: The
Gathering card ever printed. Useful if you want details on a particular card from a particular 
expansion.
<p>Even more important, by clicking the <button class="btn btn-mini"><i class="icon-search"></i></button>
<strong>Find in binders</strong> button, you can find all binders that contain that particular card.

<p>&nbsp;

<h4>III. Create your own online binder</h4>
<p>In order to make your own online binder, you need to <a href="<@spring.url '/auth/register'/>">Register</a>
and <a href="/auth/login">Sign in</a>. Once you are signed in to the site, you gain access to your
<a class="no-intercept" href="/account/dashboard">Account dashboard</a>.

<p>From there, you can
  <ul>
    <li>Edit your profile information
    <li>Create your own binders
    <li>Add cards to your want list
    <li>Do other things that are still to come
  </ul>

<p>&nbsp;

<h4>IV. Contribute to your Magic Community</h4>
<p>The idea with <strong>Cities</strong> and <strong>Meetups</strong> is to help create communities of
Magic players who are able to trade and interact on this site. In the near future, it will be possible to 
post messages to the community pages. (Right now they just list the members of that community).

<p>&nbsp;

<h4>V. Send feedback</h4>
<p>Please send any and all questions and suggestions to <a href="mailto:mtgbinder@gmail.com">mtgbinder at gmail.com</a>. 
We would also really appreciate you dropping us an email just to say hi. :)

<p>&nbsp;

<h4>VI. The future</h4>
<p>Many more things to come. Please also see the <a href="<@spring.url '/support/upcoming'/>">Upcoming Features</a>
page for more details; 