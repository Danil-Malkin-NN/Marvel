<#import "parts/common.ftlh" as c>


<@c.page>
<div>
<p>
<span><a href="/v1/public/characters">Characters</a></span>
</p>
</div>
    <div>
        <form method="post" action="/v1/public/characters" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="Введите имя" />
            <input type="text" name="tag" placeholder="Тэг">
            <input type="text" name="description" placeholder="Описание">
            <input type="file" name="file">
            <button type="submit">Добавить</button>
        </form>
    </div>

</@c.page>
