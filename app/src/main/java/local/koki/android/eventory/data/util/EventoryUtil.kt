package local.koki.android.eventory.data.util

import io.realm.RealmObject

/**
 * Created by 浩生 on 2017/01/17.
 */

class EventoryUtil :RealmObject,EventInterface {
    private var eventId: String? = null
    private var apiId: Int? = 0
    private var title: String? = null
    private var url: String? = null
    private var limit: Int? = 0
    private var accepted: Int? = 0
    private var address: String? = null
    private var place: String? = null
    private var startAt: String? = null
    private var endAt: String? = null
    private var id: Int? = 0

    public constructor()

    override fun setEventId(eventId: String?) {
        this.eventId = eventId
    }

    override fun getEventId():String? {
        return this.eventId
    }

    override fun setApiId(apiId: Int?) {
        this.apiId = apiId
    }

    override fun getApiId(): Int? {
        return this.apiId
    }

    override fun setTitle(title: String?) {
        this.title = title
    }

    override fun getTitle(): String? {
        return this.title
    }

    override fun setUrl(url: String?) {
        this.url = url
    }

    override fun getUrl(): String? {
        return this.url
    }

    override fun setLimit(limit: Int?) {
        this.limit = limit
    }

    override fun getLimit(): Int? {
        return this.limit
    }

    override fun setAccepted(accepted: Int?) {
        this.accepted = accepted
    }

    override fun getAccepted(): Int? {
        return this.accepted
    }

    override fun setAddress(address: String?) {
        this.address = address
    }

    override fun getAddress(): String? {
        return this.address
    }

    override fun setPlace(place: String?) {
        this.place = place
    }

    override fun getPlace(): String? {
        return this.place
    }

    override fun setStartAt(startAt: String?) {
        this.startAt = startAt
    }

    override fun getStartAt(): String? {
        return this.startAt
    }

    override fun setEndAt(endAt: String?) {
        this.endAt = endAt
    }

    override fun getEndAt(): String? {
        return this.endAt
    }

    override fun setId(id: Int?) {
        this.id = id
    }

    override fun getId(): Int? {
        return this.id
    }


}
