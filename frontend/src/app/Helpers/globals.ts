import {PublicationItemType} from '../Enums/PublicationItemType';
import {Book} from '../DTOs/Inventory/Book';
import {Journal} from '../DTOs/Inventory/Journal';
import {LiteraryPiece} from '../DTOs/Inventory/LiteraryPiece';
import {ItemType} from '../Enums/ItemType';

//dev
// export const BASE_URL = "https://localhost:8080/api/";

// prod
export const BASE_URL = "/api/";

export const FACTORY_TYPES = new Map([
  [PublicationItemType.BOOK, Book],
  [PublicationItemType.JOURNAL, Journal],
  [PublicationItemType.LITERARY_PIECE, LiteraryPiece],
]);

export const ITEM_TYPE_TABLE_HEADERS_SHOP = new Map([
  [ItemType.PUBLICATION_ITEM, new Map([
    ["publicationItemType", []],
    ["edition", []],
    ["format", []],
    ["purchasePrice", ["currency"]],
    ["rentalRate", ["currency"]],
    ["publicationItemStatus", []]])
  ]]);

export const PUBLICATION_ITEM_TYPE_TABLE_HEADERS_SHOP = new Map([
  [PublicationItemType.BOOK, new Map()],
  [PublicationItemType.JOURNAL, new Map([
    ["issueDate", ["date"]],
    ["issueNumber", []],
    ["issueName", []],
    ["volume", []]])],
  [PublicationItemType.LITERARY_PIECE, new Map([["literaryType", []]])]
]);

export const ITEM_TYPE_TABLE_HEADERS_REPORT = new Map([
  [ItemType.PUBLICATION_ITEM, new Map([
    ["publicationItemType", []],
    ["edition", []],
    ["format", []],
    ["purchasePrice", ["currency"]],
    ["rentalRate", ["currency"]],
    ["publicationItemStatus", []]
  ])
  ]]);


export const PUBLICATION_ITEM_TYPE_TABLE_HEADERS_REPORT = new Map([
  [PublicationItemType.BOOK, new Map()],
  [PublicationItemType.JOURNAL, new Map([
    ["issueDate", ["date"]],
    ["issueNumber", []],
    ["issueName", []],
    ["volume", []]])],
  [PublicationItemType.LITERARY_PIECE, new Map([["literaryType", []]])]
]);



