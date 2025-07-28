export class Publication {
  constructor(
    private publicationId: Number,
    private datePublished: Date,
    private isbn: String,
    private genre: String,
    private quantity: Number
  ) {}
}
