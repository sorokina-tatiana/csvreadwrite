<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Table</title>
        <link rel="stylesheet"
              href="/css/style.css">
    </head>
    <body>
        <form method="POST"
              enctype="multipart/form-data">
            File to upload:
            <input type="file"
                   name="fileToUpload"
                   id="fileToUpload">
            <input type="submit"
                   value="Upload">
            Press here to upload the file!
        </form>
        <#if message?has_content>
            <span>${message}</span>
        </#if>
<#--        <form method="POST"-->
<#--              enctype="multipart/form-data"-->
<#--              action="/download">-->
<#--            Download file:-->
<#--            <input type="file"-->
<#--                   name="fileToDownload"-->
<#--                   id="fileToDownload">-->
<#--            <input type="submit"-->
<#--                   value="Download">-->
<#--            Press here to download the file!-->

<#--            <a role="button"-->
<#--               href="path_to_file"-->
<#--               download="proposed_file_name">-->
<#--                Download-->
<#--            </a>-->
<#--        </form>-->

        <form method="post" action="/download" enctype="application/x-www-form-urlencoded">
            <input type="submit" value="Download" />
            <table>
                <#if headers??>
                    <thead>
                        <tr>
                            <#list headers as fieldName>
                                <td>${fieldName}</td>
                            </#list>
                        </tr>
                    </thead>
                </#if>

                <tbody>
                    <#if records??>
                        <#list records as record>
                            <tr>
                                <#list record as value>
                                    <td>
<#--                                        <div contenteditable>${value}</div>-->
                                        <input type="text" name=${value} value=${value} />
                                    </td>
                                </#list>
                            </tr>
                        </#list>
                    </#if>
                </tbody>
            </table>
        </form>

    </body>
</html>