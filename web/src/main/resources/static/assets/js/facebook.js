$(function () {
    'use strict'

    const root_app = "/eps"

    $("#fetchAllAgeGenderInsights").click(function () {
        $("#insight-info").text("");
        // apply loading function here
        fetch(root_app +'/api/v0/facebook/insights/all/age-gender', {
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                // create a custom table in $
                var table = $("<table>").addClass("table table-sm");
                var thead = $("<thead>");
                thead.append("<tr><th>info</th><th>value</th></tr>");
                table.append(thead);

                var tbody = $("<tbody>");

                // this means no errors
                if(data.error == null) {
                    if(data.totalDataPoint != null) {
                        tbody.append("<tr><td><b>totalDataPoint</b></td><td>"+data.totalDataPoint+"</td></tr>");
                    }
                    if(data.newDataPoint != null) {
                        tbody.append("<tr><td><b>newDataPoint</b></td><td>"+data.newDataPoint+"</td></tr>");
                    }
                    if(data.startUpdateFrom != null) {
                        tbody.append("<tr><td><b>dataFrom</b></td><td>"+data.startUpdateFrom.substring(0,10) +"--->"+ data.startUpdateTo.substring(0,10)+"</td></tr>");
                    }
                    if(data.endUpdateFrom != null) {
                        tbody.append("<tr><td><b>dataTo</b></td><td>"+data.endUpdateFrom.substring(0,10) +"--->"+ data.endUpdateTo.substring(0,10)+"</td></tr>");
                    }

                }else {
                    tbody.append("<tr><td><b>error</b></td><td>"+data.error.message+"</td></tr>");
                }
                table.append(tbody);
                $("#insight-info").append(table);


            });
    }); // #fetchAllAgeGenderInsights ends

    $("#updateAllAgeGenderInsights").click(function () {
        $("#insight-save-info").text("");
        let obj = JSON.parse(localStorage.getItem("adInsightsAgeGenderList"));
        $.ajax({
            type: "POST",
            url: root_app +"/api/v0/facebook/insights/all/age-gender",
            data: obj,
            contentType: 'application/json',
            error: function (xhr, status, err) {
                console.error(xhr, status, err.toString());
            }

        })
        //
        // fetch(root_app +'/api/v0/facebook/insights/all/age-gender', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: obj
        //
        // })
        //     .then(response => response.json())
        //     .then(data => {
        //         localStorage.removeItem("adInsightsAgeGenderList");
        //         $("#insight-save-info").addClass("h2").append(data.totalNumberOfSavedInsights);
        //         $("#insight-save-info-details").append("Age and Gender insights are saved");
        //
        //
        //     })
        //     .catch(err => {
        //         console.log(err);
        //         $("#insight-save-info").text("first You need to have fetch data!!!");
        //     })
    }); //updateAllAgeGenderInsights ends


});