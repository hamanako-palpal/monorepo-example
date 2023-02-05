import type { CodegenConfig } from '@graphql-codegen/cli'
 
const config: CodegenConfig = {
  schema: '../../../schemas/graphql/*.graphqls',//'http://localhost:8080/graphql',
  // documents: '../../../schemas/graphql/model.graphql',
  generates: {
    './src/graphql/generated.ts': {
      plugins: [
        'typescript',
        'typescript-operations',
        'graphql-codegen-svelte-apollo'
      ]
    }
  }
}
export default config