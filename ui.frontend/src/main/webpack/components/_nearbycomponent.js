import $ from "jquery";

(function () {
    "use strict";
    var selectors = {
        self: '[data-cmp-is="cmp-nearbycomponent"]',

    };
    function Nearby(config) {
        let queryParams = new URLSearchParams(window.location.search);
        let jsonData;
        let cityName = $('#cities');
        let prices = $('#prices');

        function dataFromQuery() {
            for (let i = 0; i < jsonData.data.length; i++) {
                for (let j = 0; j < jsonData.data[i].categories.length; j++) {
                    
                    if (queryParams.get('cat') == jsonData.data[i].categories[j]) {
                        console.log(jsonData.data[i]);

                    }
                }
            }
        }

        function addElements() {
            cityName.empty();
            prices.empty();
            for (let i = 0; i < jsonData.data.length; i++) {
                let elementForCity = `<option value="">${jsonData.data[i].area}</option>`;
                let elementForPrice = `<option value="">${jsonData.data[i].price}</option>`;
                cityName.append(elementForCity);
                prices.append(elementForPrice);
            }
        }

        function searchData() {
            $('.cmp-nearby__filter-button').on('click', function (e) {
                e.preventDefault();
                let area = $('#cities :selected').text();
                let price = $('#prices :selected').text();
                let inputVal = $('#location').val();


                for (let i = 0; i < jsonData.data.length; i++) {
                    if ((jsonData.data[i].area == area) && (jsonData.data[i].price == price) || (inputVal.toLowerCase() == jsonData.data[i].address.toLowerCase())) {
                        console.log(jsonData.data[i]);

                    }

                }

            })
        }

        function init(config) {
            config.element.removeAttribute("data-cmp-is");
            $('.cmp-nearby__menu-link').on('click', function (e) {
                e.preventDefault();
                queryParams.set('cat', $(this).attr('data-query'));
                window.history.replaceState({}, '', `${location.pathname}?${queryParams.toString()}`);
                dataFromQuery();
            });

            $('.cmp-nearby__menu-wrapper-contain').on('click', function (e) {
                $('.cmp-nearby__menu-wrapper-contain').removeClass('active');
                $(this).addClass('active');
            })

            $.ajax({
                url: '/content/dam/wknd/data.json',
                type: 'GET',
                dataType: 'json',
                success: function (resp) {

                    jsonData = resp;

                    addElements();
                    searchData();
                    dataFromQuery();
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }

            });


        }

        if (config && config.element) {
            init(config);
        }
    }

    function onDocumentReady() {
        var elements = document.querySelector(selectors.self);
        new Nearby({ element: elements });
        var MutationObserver = window.MutationObserver || window.WebKitMutationObserver || window.MozMutationObserver;
        var body = document.querySelector("body");
        var observer = new MutationObserver(function (mutations) {
            mutations.forEach(function (mutation) {
                var nodesArray = [].slice.call(mutation.addedNodes);
                if (nodesArray.length > 0) {
                    nodesArray.forEach(function (addedNode) {
                        if (addedNode.querySelectorAll) {
                            var elementsArray = [].slice.call(addedNode.querySelectorAll(selectors.self));
                            elementsArray.forEach(function (element) {
                                new Nearby({ element: element });
                            });
                        }
                    });
                }
            });
        });

        observer.observe(body, {
            subtree: true,
            childList: true,
            characterData: true
        });
    }

    if (document.readyState !== "loading") {
        onDocumentReady();
    } else {
        document.addEventListener("DOMContentLoaded", onDocumentReady);
    }

}());
