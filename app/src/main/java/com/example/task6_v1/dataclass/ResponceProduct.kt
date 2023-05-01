package com.example.task6_v1.dataclass

import com.google.gson.annotations.SerializedName

data class ResponceProduct(
	//val response: List<ResponseItem?>? = null

	/*val category: String,
	val description: String,
	val id: Int,
	val image: String,
	val price: String,
	val rating: Rating,
	val title: String,
*/


	/*@field:SerializedName("ResponseSampleProduct")
	val responseSampleProduct: List<ResponseItem?>? = null*/

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("description")
	var description: String? = null,

	@field:SerializedName("id")
	var id: Int? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null,

	@field:SerializedName("title")
	var title: String? = null


)

data class Rating(
	/*val rate: String,
	val count: Int*/
	@field:SerializedName("rate")
	val rate: String? = null,

	@field:SerializedName("count")
	val count: Int? = null
)

/*
data class ResponseItem(
	*/
/*val image: String? = null,
	val price: Any? = null,
	val rating: Rating? = null,
	val description: String? = null,
	val id: Int? = null,
	val title: String? = null,
	val category: String? = null*//*


	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("price")
	val price: Any? = null,

	@field:SerializedName("rating")
	val rating: Rating? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: String? = null
)
*/

