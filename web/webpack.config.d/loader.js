var ringConfig = require('@jetbrains/ring-ui/webpack.config');

ringConfig.config.module.rules.forEach(function(element) {
    config.module.rules.push(element)
})

