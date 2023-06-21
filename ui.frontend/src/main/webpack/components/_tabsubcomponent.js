import $ from "jquery";

(function () {
    "use strict";
    var selectors = {
        self: '[data-cmp-is="cmp-tabsubcomponent"]',

    };
    function journalism(config) {
        function init(config) {
            let amount = $('.cmp-tabsubcomponent__rightside__planaction__amount');
            let listItem = $('.cmp-tabsubcomponent__rightside__planaction__amount__listitem');
            let inputImage = $('.cmp-tabsubcomponent__rightside__planaction__amount-image');
            config.element.removeAttribute("data-cmp-is");

            $('.radiobtn').on('click', function () {
                $(this).closest(amount).find('.ctaBtnText').html($(this).closest(amount).find('.ctaBtnText').data('cta-text') + ' '
                    + $(this).attr('data-amount') + $(this).attr('data-sufix'));

                $(this).closest(amount).find(inputImage).hide();
                console.log("click fromfirst: " + $(this).attr('data-amount'))
                $(this).closest(amount).find('.ctaBtnText').removeClass('selected-other');

            });

            $('.other').on('click', function () {
                $(this).closest(amount).find(inputImage).show();
                $(this).closest(amount).find('.ctaBtnText').addClass('selected-other');
                $(this).closest(amount).find('.ctaBtnText').html($(this).closest(amount).find('.ctaBtnText').data('cta-text') + ' ' + $(this).closest(amount).find('.ctaTextInput').val())
            })

            $('.ctaTextInput').on('input', function () {
                $(this).closest(amount).find('.ctaBtnText').addClass('change-color');
                $(this).closest(amount).find('.ctaBtnText').html($(this).closest(amount).find('.ctaBtnText').data('cta-text') + ' $' + $(this).val()
                    + $(this).closest(amount).find(listItem).attr('data-sufix'))
            })

            $('.cmp-tabs__tab').on('click', function () {

                $('.ctaBtnText').html($('.ctaBtnText').data('cta-text'))
                $('.ctaBtnText').addClass('selected-other');
                
            })
            $('.cmp-tabs__tab').each(function () {
                
                $(this).on('click', function () {
                    
                    
                    $('.cmp-tabsubcomponent')[$(this).index()].find('.radiobtn:first').trigger('click');
                   
                })
            })


        }

        if (config && config.element) {
            init(config);
        }
    }

    function onDocumentReady() {
        var elements = document.querySelector(selectors.self);
        new journalism({ element: elements });
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
                                new journalism({ element: element });
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
