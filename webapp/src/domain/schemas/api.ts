import { fetchApi } from "../../services/api"
import { Schema } from "./reducers"
import { Access, accessName } from "../../auth"
const base = "/api/schema"
const endPoints = {
    base: () => `${base}`,
    crud: (id: number | string) => `${base}/${id}/`,
    resetToken: (id: number) => `${base}/${id}/resetToken`,
    dropToken: (id: number) => `${base}/${id}/dropToken`,
    updateAccess: (id: number, owner: string, access: Access) =>
        `${base}/${id}/updateAccess?owner=${owner}&access=${accessName(access)}`,
    extractor: () => `${base}/extractor`,
    extractorForSchema: (schemaId: number) => `${base}/extractor?schemaId=${schemaId}`,
    extractorForAccessor: (accessor: string) => `${base}/extractor?accessor=${accessor}`,
    deprecated: (id: number) => `${base}/extractor/${id}/deprecated`,
    findUsages: (accessor: string) => `${base}/findUsages?accessor=${encodeURIComponent(accessor)}`,
    testJsonPath: (jsonpath: string) => `/api/sql/testjsonpath?query=${encodeURIComponent(jsonpath)}`,
    transformers: (schemaId: number) => `${base}/${schemaId}/transformers`,
    allTransformers: () => `${base}/allTransformers`,
}
export const all = () => {
    return fetchApi(endPoints.base(), null, "get")
}
export const add = (payload: Schema) => {
    return fetchApi(endPoints.base(), payload, "post")
}
export const getByName = (name: string) => {
    return fetchApi(endPoints.crud(name), null, "get")
}
export const getById = (id: number) => {
    return fetchApi(endPoints.crud(id), null, "get")
}

export const resetToken = (id: number) => fetchApi(endPoints.resetToken(id), null, "post", {}, "text")

export const dropToken = (id: number) => fetchApi(endPoints.dropToken(id), null, "post")

export const updateAccess = (id: number, owner: string, access: Access) => {
    // TODO: fetchival does not support form parameters, it tries to JSONify everything
    return fetchApi(endPoints.updateAccess(id, owner, access), null, "post", {}, "response")
    //                   "owner=" + encodeURIComponent(owner) + "&access=" + encodeURIComponent(access),
    //                   'post', { 'content-type' : 'application/x-www-form-urlencoded'}, 'response')
}

export const listExtractors = (schemaId?: number, accessor?: string) => {
    let path = endPoints.extractor()
    if (schemaId !== undefined) {
        path = endPoints.extractorForSchema(schemaId)
    } else if (accessor !== undefined) {
        path = endPoints.extractorForAccessor(accessor)
    }
    return fetchApi(path, null, "get")
}

export const addOrUpdateExtractor = (extractor: Extractor) => fetchApi(endPoints.extractor(), extractor, "post")

export const deleteSchema = (id: number) => fetchApi(endPoints.crud(id), null, "delete")

export const testJsonPath = (jsonpath: string) => fetchApi(endPoints.testJsonPath(jsonpath), null, "get")

export function findUsages(accessor: string) {
    return fetchApi(endPoints.findUsages(accessor), null, "get")
}

export function findDeprecated(extractorId: number) {
    return fetchApi(endPoints.deprecated(extractorId), null, "get")
}

export function listTransformers(schemaId: number) {
    return fetchApi(endPoints.transformers(schemaId), null, "get")
}

export function addOrUpdateTransformer(transformer: Transformer) {
    return fetchApi(endPoints.transformers(transformer.schemaId), transformer, "post")
}

export function allTransformers(): Promise<TransformerInfo[]> {
    return fetchApi(endPoints.allTransformers(), null, "get")
}

export type ValidationResult = {
    valid: boolean
    reason: string
    errorCode: number
    sqlState: string
}

export interface Extractor {
    id: number
    accessor: string
    schema?: string
    schemaId?: number
    jsonpath?: string
    // upload-only fields
    deleted?: boolean
    changed?: boolean
    // temprary fields
    oldName?: string
    validationTimer?: any
    validationResult?: ValidationResult
}

export interface AccessorLocation {
    type: "TAGS" | "VARIABLE" | "VIEW" | "REPORT"
    testId: number
    testName: string
}

// eslint-disable-next-line @typescript-eslint/no-empty-interface
export interface AccessorInTags extends AccessorLocation {}

export interface AccessorInVariable extends AccessorLocation {
    variableId: number
    variableName: string
}

export interface AccessorInView extends AccessorLocation {
    viewId: number
    viewName: string
    componentId: number
    header: string
}

export interface AccessorInReport extends AccessorLocation {
    configId: number
    title: string
    where: "component" | "filter" | "category" | "series" | "label"
    name: string | null
}

export type NamedJsonPath = {
    name: string
    jsonpath: string
    validationTimer?: any
    validationResult?: ValidationResult
}

export type Transformer = {
    id: number
    schemaId: number
    schemaUri: string
    schemaName: string
    name: string
    description: string
    targetSchemaUri?: string
    extractors: NamedJsonPath[]
    function?: string
    owner: string
    access: Access
    modified?: boolean
}

export type TransformerInfo = {
    schemaId: number
    schemaUri: string
    schemaName: string
    transformerId: number
    transformerName: string
}
