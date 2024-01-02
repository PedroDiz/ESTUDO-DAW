const ESLintPlugin = require('eslint-webpack-plugin');
module.exports = {
  mode: 'development',
  devServer: {
    port: 8000,
    historyApiFallback: true,
    compress: false
  },
  resolve: {
    extensions: [".js", ".ts", ".tsx"]
  },
  plugins: [
    new ESLintPlugin({
      extensions: ['js', 'jsx', 'ts', 'tsx']
    })
  ],
  module: {
    rules: [
        {
            test: /\.tsx?$/,
            use: 'ts-loader',
            exclude: /node_modules/
        },
    ]
  }
};