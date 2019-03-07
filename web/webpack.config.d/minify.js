var UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports.optimization =
    {
        minimize: true,
        minimizer: [
            // we specify a custom UglifyJsPlugin here to get source maps in production
            new UglifyJsPlugin({
                cache: true,
                parallel: true,
                uglifyOptions: {
                    compress: true,
                    ecma: 6,
                    mangle: true
                },
                sourceMap: true
            })
        ]

    }
;