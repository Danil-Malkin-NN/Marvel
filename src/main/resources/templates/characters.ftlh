<#import "parts/common.ftlh" as c>
<#import "parts/pager.ftlh" as p>


<@p.pager url page/>
 <span><a href="/v1/public/characters/add">Add</a></span>
<@c.page>
<div class="card-columns">
   <#list page.content as character>
   <div class="card">
       <img src="/img/${character.image}" height="300", width="200">
       <div class="container">
           <h4><b>${character.name}</b></h4>
           <p class="card-text">${character.description}</p>
       </div>
   </div>
    <#else>
    No message
    </#list>
</div>
</@c.page>